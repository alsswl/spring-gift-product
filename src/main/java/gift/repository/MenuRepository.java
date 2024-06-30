package gift.repository;

import gift.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MenuRepository {
    private Long id = 1L;

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public MenuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Menu save(Menu menu) {
        var sql = "insert into menus (id, name, price,imageUrl) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, id++, menu.getName(), menu.getPrice(), menu.getImageUrl());
        return menu;
    }

    public Menu findById(Long id) {
        String sql = "select id, name, price,imageUrl from menus where id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new Menu(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("imageUrl")
                ),
                id
        );
    }

    public List<Menu> findAll() {
        String sql = "select id, name, price,imageUrl from menus";
        List<Menu> menus = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    Menu menu = new Menu(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("price"),
                            resultSet.getString("imageUrl")
                    );
                    return menu;
                });
        return menus;
    }

    public void update(Long id, String name, int price, String imageUrl) {
        String sql = "UPDATE menus SET name = ?, price = ?,imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, price, imageUrl, id);
    }

    public Long delete(Long id) {
        var sql = "delete from menus where id = ?";
        jdbcTemplate.update(sql, id);
        return id;
    }

}
