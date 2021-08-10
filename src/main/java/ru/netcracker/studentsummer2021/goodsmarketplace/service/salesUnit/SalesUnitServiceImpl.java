package ru.netcracker.studentsummer2021.goodsmarketplace.service.salesUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.salesUnit.SalesUnitDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.salesUnit.SalesUnitPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.SalesUnit;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ProductRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.SalesUnitRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.UsersRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.product.ProductConverter;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.product.ProductServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service("salesUnitServiceImpl")
public class SalesUnitServiceImpl implements SalesUnitService {

    private final SalesUnitRepository salesUnitRepository;
    private final SalesUnitConverter salesUnitConverter;
    private final UsersRepository usersRepository;
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final ProductServiceImpl productService;


    @Autowired
    public SalesUnitServiceImpl(SalesUnitRepository salesUnitRepository, SalesUnitConverter salesUnitConverter, UsersRepository usersRepository, ProductRepository productRepository, ProductConverter productConverter, ProductServiceImpl productService) {
        this.salesUnitRepository = salesUnitRepository;
        this.salesUnitConverter = salesUnitConverter;
        this.usersRepository = usersRepository;
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.productService = productService;
    }

    @Override
    public ResponseEntity<?> save(User user, SalesUnitDTO salesUnitDTO) {
        return new ResponseEntity<>(salesUnitRepository.save(
                salesUnitConverter.fromDTOToSalesUnit(usersRepository.findByUsername(user.getUsername()),salesUnitDTO)),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> get(Long salesUnitId) {
        if(salesUnitRepository.findById(salesUnitId).isEmpty()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(salesUnitConverter.fromSalesUnitToDTO(
                salesUnitRepository.getById(salesUnitId),
                productService.getProductDTO(salesUnitRepository.getById(salesUnitId).getProduct())),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeInfo(User user, SalesUnitDTO salesUnitDTO) {
        if(salesUnitRepository.findById(salesUnitDTO.getId()).isEmpty()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SalesUnit salesUnit = salesUnitRepository.getById(salesUnitDTO.getId());
        salesUnit.setQuantity(salesUnitDTO.getQuantity());
        salesUnit.setShipCost(salesUnitDTO.getShipCost());
        salesUnit.setPrice(salesUnitDTO.getPrice());
        salesUnit.setDesc(salesUnitDTO.getDesc());
        return new ResponseEntity<>(salesUnitRepository.save(salesUnit), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(User user, Long salesUnitId) {
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin")) ||
                usersRepository.findByUsername(user.getUsername()).getShopId().equals(salesUnitRepository.getById(salesUnitId).getShop())){
            if(salesUnitRepository.findById(salesUnitId).isEmpty()){
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            salesUnitRepository.deleteById(salesUnitId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<?> getForCity(User user, Long suId) {
        Users users = usersRepository.findByUsername(user.getUsername());
        return new ResponseEntity<>(salesUnitRepository.getForCity(suId, users.getId())
                .stream()
                .map((s) -> salesUnitConverter.fromSalesUnitToDTO(s,
                        productService.getProductDTO(salesUnitRepository.getById(s.getId()).getProduct())))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll(User user, Long shopId) {
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin")) ||
                usersRepository.findByUsername(user.getUsername()).getShopId().equals(shopId)){
            if(salesUnitRepository.findSalesUnitByShop(shopId).isEmpty()){
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<SalesUnitPublicDTO> salesUnit = salesUnitRepository.findSalesUnitByShop(shopId)
                    .stream()
                    .map((s) -> salesUnitConverter.fromSalesUnitToDTO(s,
                            productService.getProductDTO(salesUnitRepository.getById(s.getId()).getProduct())))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(salesUnit, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
