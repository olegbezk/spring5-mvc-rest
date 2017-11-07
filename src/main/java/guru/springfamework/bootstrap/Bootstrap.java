package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    private final CustomerRepository customerRepository;

    private final VendorRepository vendorRepository;

    @Autowired
    public Bootstrap(
            final CategoryRepository categoryRepository,
            final CustomerRepository customerRepository,
            final VendorRepository vendorRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(final String... strings) throws Exception {

        loadCategories();

        loadCustomers();

        loadVendors();
    }

    private void loadCustomers() {
        Customer first = new Customer();
        first.setFirstname("Oleg");
        first.setLastname("B");

        Customer second = new Customer();
        second.setFirstname("Nick");
        second.setLastname("C");

        Customer third = new Customer();
        third.setFirstname("Dasha");
        third.setLastname("K");

        Customer forth = new Customer();
        forth.setFirstname("Nikki");
        forth.setLastname("Six");

        customerRepository.save(first);
        customerRepository.save(second);
        customerRepository.save(third);
        customerRepository.save(forth);

        System.out.println("Customer data loaded = " + customerRepository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Category data loaded = " + categoryRepository.count());
    }

    private void loadVendors() {
        Vendor vendor = new Vendor();
        vendor.setName("YOYO");

        Vendor vendor2 = new Vendor();
        vendor2.setName("LEGO");

        vendorRepository.save(vendor);
        vendorRepository.save(vendor2);

        System.out.println("Vendor data loaded = " + vendorRepository.count());
    }
}
