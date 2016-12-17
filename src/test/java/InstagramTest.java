
import com.leapfrog.instascrapper.Instagram;
import com.leapfrog.instascrapper.exception.InstagramException;
import com.leapfrog.instascrapper.exception.InstagramNotFoundException;
import com.leapfrog.instascrapper.model.Account;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class InstagramTest {
    Instagram instagram;
   
        @Before
    public void setUp() throws Exception {
        instagram = new Instagram();
    }
    
    @Test
    public void testGetAccountByUsername() throws IOException, InstagramException, InstagramNotFoundException{
    Account account = instagram.getAccountByUsername("Kevin");
    
        assertEquals("Kevin", account.username);
}
}
