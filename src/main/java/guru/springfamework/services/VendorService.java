package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDto;
import guru.springfamework.api.v1.model.VendorListDto;

import java.util.List;

public interface VendorService {

    VendorListDto getAllVendors();

    VendorDto getVendorById(Long id);

    VendorDto createNewVendor(VendorDto vendorDto);

    VendorDto saveVendorByDto(Long id, VendorDto vendorDto);

    VendorDto patchVendor(Long id, VendorDto vendorDto);

    void deleteVendorById(Long id);
}
