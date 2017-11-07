package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDto;
import guru.springfamework.api.v1.model.VendorListDto;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    private static final String NAME = "YOYO";

    @Autowired
    private VendorService vendorService;

    @Mock
    private VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void getAllVendors() throws Exception {
        final Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName(NAME);

        final Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("LEGO");

        when(vendorRepository.findAll()).thenReturn(asList(vendor1, vendor2));

        final VendorListDto allVendors = vendorService.getAllVendors();

        assertEquals(2, allVendors.getVendors().size());
    }

    @Test
    public void getVendorById() throws Exception {
        final Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(NAME);

        when(vendorRepository.findById(eq(1L))).thenReturn(Optional.of(vendor));

        final VendorDto vendorById = vendorService.getVendorById(1L);

        assertEquals(vendorById.getName(), vendor.getName());
    }

    @Test
    public void createNewVendor() throws Exception {
        final Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(NAME);

        final VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        final VendorDto vendorDto1 = vendorService.createNewVendor(vendorDto);

        assertEquals(vendorDto.getName(), vendorDto1.getName());
    }

    @Test
    public void saveVendorByDto() throws Exception {
        final Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(NAME);

        final VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        final VendorDto vendorDto1 = vendorService.saveVendorByDto(1L, vendorDto);

        assertEquals(vendorDto.getName(), vendorDto1.getName());
    }

    @Test
    public void deleteVendorById() throws Exception {
        vendorService.deleteVendorById(1L);

        verify(vendorRepository).deleteById(eq(1L));
    }

}