package com.test.spring.security.login.payload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.spring.security.login.models.ERole;
import com.test.spring.security.login.models.Product;
import com.test.spring.security.login.models.Role;
import com.test.spring.security.login.models.User;
import com.test.spring.security.login.repository.ProductRepository;
import com.test.spring.security.login.repository.RoleRepository;
import com.test.spring.security.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class LoadData {

    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoadData(RoleRepository roleRepo, UserRepository userRepo, ProductRepository productRepo, PasswordEncoder passwordEncoder) throws IOException {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.passwordEncoder = passwordEncoder;
        loadRole();
        loadUser();
        loadProduct();
    }

    private void loadRole() throws IOException {
        InputStream inputStreamRoles = new ClassPathResource("roles.json",
                this.getClass().getClassLoader()).getInputStream();
        String text = new BufferedReader(new InputStreamReader(inputStreamRoles))
                .lines().collect(Collectors.joining("\n"));
        //String text = IOUtils.readInputStreamToString(inputStreamRoles, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> clientFilterJson = Arrays.asList(mapper.readValue(text, Map[].class));
        for (Map<String, String> map : clientFilterJson) {
            try {
                ERole eRole = ERole.valueOf(map.get("name"));
                if(!roleRepo.existsByName(eRole)){
                    Role role = new Role(eRole);
                    roleRepo.save(role);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void loadUser() throws IOException {
        InputStream inputStreamRoles = new ClassPathResource("users.json",
                this.getClass().getClassLoader()).getInputStream();
        //String text = IOUtils.readInputStreamToString(inputStreamRoles, StandardCharsets.UTF_8);
        String text = new BufferedReader(new InputStreamReader(inputStreamRoles))
                .lines().collect(Collectors.joining("\n"));
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> clientFilterJson = Arrays.asList(mapper.readValue(text, Map[].class));
        for (Map<String, Object> map : clientFilterJson) {
            try {
                if(!userRepo.existsByUsername(String.valueOf(map.get("username"))) && !userRepo.existsByEmail(String.valueOf(map.get("email")))){
                    Set<Role> roleSet = new HashSet<>();
                    ArrayList<String> strRoles = (ArrayList<String>) map.get("roles");
                    strRoles.forEach(roleString ->{
                        Role netRole;
                        if(roleString.equalsIgnoreCase("admin"))
                            netRole = roleRepo.findByName(ERole.ROLE_ADMIN).get();
                        else if(roleString.equalsIgnoreCase("supervisor"))
                            netRole = roleRepo.findByName(ERole.ROLE_SUPERVISOR).get();
                        else
                            netRole = roleRepo.findByName(ERole.ROLE_USER).get();

                        roleSet.add(netRole);
                    });
                    User user = new User();
                    user.setFullname(String.valueOf(map.get("fullname")));
                    user.setUsername(String.valueOf(map.get("username")));
                    user.setEmail(String.valueOf(map.get("email")));
                    user.setPassword(passwordEncoder.encode(String.valueOf(map.get("password"))));
                    user.setRoles(roleSet);
                    userRepo.save(user);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void loadProduct() throws IOException {
        InputStream inputStreamRoles = new ClassPathResource("products.json",
                this.getClass().getClassLoader()).getInputStream();
        //String text = IOUtils.readInputStreamToString(inputStreamRoles, StandardCharsets.UTF_8);
        String text = new BufferedReader(new InputStreamReader(inputStreamRoles))
                .lines().collect(Collectors.joining("\n"));
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> clientFilterJson = Arrays.asList(mapper.readValue(text, Map[].class));
        for (Map<String, Object> map : clientFilterJson) {
            try {
                String productName = String.valueOf(map.get("productname"));
                if(!productRepo.existsByProductname(productName)){
                    Product product = new Product();
                    product.setProductname(productName);
                    product.setPrice(Double.parseDouble(String.valueOf(map.get("price"))));
                    product.setDatetime(String.valueOf(map.get("datetime")));
                    productRepo.save(product);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
