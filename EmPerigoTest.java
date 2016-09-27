import org.junit.Assert;
import org.junit.Test;

public class EmPerigoTest {

	@Test
	public void test1() {
		Assert.assertEquals(3, EmPerigo.resolverLinha("05e0"));
	}

	@Test
	public void test2() {
		Assert.assertEquals(5, EmPerigo.resolverLinha("01e1"));
	}

	@Test
	public void test3() {
		Assert.assertEquals(9, EmPerigo.resolverLinha("12e0"));
	}

	@Test
	public void test4() {
		Assert.assertEquals(9, EmPerigo.resolverLinha("20e0"));
	}

	@Test
	public void test5() {
		Assert.assertEquals(9, EmPerigo.resolverLinha("20e0"));
	}

}
