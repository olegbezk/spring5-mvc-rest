package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public Bootstrap(final CategoryRepository categoryRepository, final CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(final String... strings) throws Exception {

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

        Customer first = new Customer();
        first.setName("Oleg");

        Customer second = new Customer();
        second.setName("Nick");

        Customer third = new Customer();
        third.setName("Dasha");

        Customer forth = new Customer();
        forth.setName("Nikki");

        customerRepository.save(first);
        customerRepository.save(second);
        customerRepository.save(third);
        customerRepository.save(forth);

        System.out.println("Customer data loaded = " + customerRepository.count());
    }
}
