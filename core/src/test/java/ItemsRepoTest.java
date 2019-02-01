import com.techvariable.traxpense.core.Main;
import com.techvariable.traxpense.core.models.Category;
import com.techvariable.traxpense.core.models.Item;
import com.techvariable.traxpense.core.repositories.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author utpal
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@DataJpaTest
@Transactional
public class ItemsRepoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void whenFindAll_thenReturnItems() {
        Category c = new Category("c1", "c1");
        entityManager.persist(c);
        Item item1 = new Item("exp1","test1",30.0, new Date(), c);
        Item item2 = new Item("exp2","test2",40.0, new Date(), c);
        entityManager.persist(item1);
        entityManager.persist(item2);
        entityManager.flush();

        Collection<Item> found = itemRepository.findAll();

        Assertions.assertThat(found.size())
                .isEqualTo(2);

    }

    @Test
    public void whenFindById_thenReturnItem() {
        Category c = new Category("c1", "c1");
        entityManager.persist(c);
        Item item1 = new Item("exp1","test1",30.0, new Date(), c);
        entityManager.persist(item1);
        entityManager.flush();

        Item found = itemRepository.findById(1);

        Assertions.assertThat(found.getHeading())
                .isEqualTo(item1.getHeading());
    }

    @Test
    public void whenFindByCreatedDate_thenReturnItems() {
        Category c = new Category("c1", "c1");
        entityManager.persist(c);
        Item item1 = new Item("exp1","test1",30.0, new Date(), c);
        entityManager.persist(item1);
        entityManager.flush();

        List<Item> found = itemRepository.findHavingCreatedDate(new Date());

        Assertions.assertThat(found.get(0).getHeading())
                .isEqualTo(item1.getHeading());
    }

    @Test
    public void whenDeleteById_thenSetFlag() {
        Category c = new Category("c1", "c1");
        entityManager.persist(c);
        Item item1 = new Item("exp1","test1",30.0, new Date(), c);
        entityManager.persist(item1);
        entityManager.flush();

        itemRepository.deleteById(item1.getId());
        entityManager.flush();

        Item found = (Item) entityManager.getEntityManager()
                .createNativeQuery("SELECT * from items where is_deleted = 1", Item.class)
                .getSingleResult();

        Assertions.assertThat(found.getHeading())
                .isEqualTo(item1.getHeading());

    }

    @Test
    public void whenDeleteByItem_thenSetFlag() {
        Category c = new Category("c1", "c1");
        entityManager.persist(c);
        Item item1 = new Item("exp1","test1",30.0, new Date(), c);
        entityManager.persist(item1);
        entityManager.flush();

        Item item2 = new Item("exp1","test1",30.0, new Date(), c);
        item2.setId(item1.getId());
        itemRepository.delete(item2);
        entityManager.flush();

        Item found = (Item) entityManager.getEntityManager()
                .createNativeQuery("SELECT * from items where is_deleted = 1", Item.class)
                .getSingleResult();

        Assertions.assertThat(found.getHeading())
                .isEqualTo(item1.getHeading());

    }
}
