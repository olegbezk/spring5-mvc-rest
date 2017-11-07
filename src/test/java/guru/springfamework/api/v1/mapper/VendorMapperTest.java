package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDto;
import guru.springfamework.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    private static final String NAME = "YOYO";

    private VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDto() throws Exception {
        final Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(NAME);

        final VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendor);

        assertEquals(vendor.getName(), vendorDto.getName());
    }

    @Test
    public void vendorDtoToVendor() throws Exception {
        final VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME);

        final Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDto);

        assertEquals(vendorDto.getName(), vendor.getName());
    }

}