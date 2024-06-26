package gift.Menu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/menu")
public class MenuController {
    MenuRepository menuRepository = new MenuRepository();
    MenuService menuService = new MenuService(menuRepository);

    @PostMapping
    public Menu save(
            @RequestBody MenuRequest request
    ) {
        Menu newMenu = menuService.save(request.name(),request.price(),request.imageUrl());
        return newMenu;
    }

    @GetMapping
    public List<Menu> read(){
        List<Menu> menus = menuService.findall();
        return menus;
    }

    @DeleteMapping("/{id}")
    public Long delete(
            @PathVariable("id") Long id
    ){
        Long deletedId = menuService.delete(id);
        return deletedId;
    }

    @PutMapping("/{id}")
    public Menu update(
            @PathVariable("id") Long id,
            @RequestBody MenuRequest request
    ){
        Menu updatedMenu = menuService.update(
                id,
                request.name(),
                request.price(),
                request.imageUrl()
        );
        return updatedMenu;
    }


}
