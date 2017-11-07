package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDto;
import guru.springfamework.api.v1.model.VendorListDto;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.exceptions.ResourceNotFoundException;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    private final VendorMapper vendorMapper;

    @Autowired
    public VendorServiceImpl(final VendorRepository vendorRepository, final VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorListDto getAllVendors() {
        final List<VendorDto> collect = vendorRepository.findAll().stream()
                .map(vendor -> {
                    final VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendor);
                    vendorDto.setVendorUrl(getVendorUrl(vendor.getId()));

                    return vendorDto;
                })
                .collect(Collectors.toList());

        return new VendorListDto(collect);
    }

    @Override
    public VendorDto getVendorById(final Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDto)
                .map(vendorDto -> {
                    vendorDto.setVendorUrl(getVendorUrl(id));

                    return vendorDto;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDto createNewVendor(final VendorDto vendorDto) {
        return saveAndReturnDto(vendorMapper.vendorDtoToVendor(vendorDto));
    }

    @Override
    public VendorDto saveVendorByDto(final Long id, final VendorDto vendorDto) {
        final Vendor vendor = vendorRepository.save(vendorMapper.vendorDtoToVendor(vendorDto));
        vendor.setId(id);

        return saveAndReturnDto(vendor);
    }

    @Override
    public VendorDto patchVendor(final Long id, final VendorDto vendorDto) {
        return vendorRepository.findById(id).map(vendor -> {

            if (vendorDto.getName() != null) {
                vendor.setName(vendorDto.getName());
            }

            return saveAndReturnDto(vendor);

        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(final Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDto saveAndReturnDto(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDto returnDto = vendorMapper.vendorToVendorDto(savedVendor);
        returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return returnDto;
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}
