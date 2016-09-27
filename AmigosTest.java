import org.junit.Assert;
import org.junit.Test;

public class AmigosTest {

	@Test
	public void test1() {
		Assert.assertEquals("{ABCDE}", Amigos.resolverLinha("{ABC}+{CDE}"));
	}

	@Test
	public void test2() {
		Assert.assertEquals("{ABD}", Amigos.resolverLinha("{ABCD}-{CZ}"));
	}

	@Test
	public void test3() {
		Assert.assertEquals("{AB}", Amigos.resolverLinha("{ABE}*{ABCD}"));
	}

	@Test
	public void test4() {
		Assert.assertEquals("{ABC}", Amigos.resolverLinha("{ABC}"));
	}

	@Test
	public void test5() {
		Assert.assertEquals("{ABCDEFGZ}", Amigos.resolverLinha("{ABC}+{DEFG}+{Z}+{}"));
	}

	@Test
	public void test6() {
		Assert.assertEquals("{ABCDEFGZ}", Amigos.resolverLinha("{ABC}+{}+{DEFG}+{Z}"));
	}

	@Test
	public void test7() {
		Assert.assertEquals("{}", Amigos.resolverLinha("{ABC}-{ABC}"));
	}

	@Test
	public void test8() {
		Assert.assertEquals("{A}", Amigos.resolverLinha("{ABCD}-{BC}-{D}"));
	}

	@Test
	public void test9() {
		Assert.assertEquals("{AB}", Amigos.resolverLinha("{ABCDE}-{E}-{D}-{C}"));
	}

	@Test
	public void test10() {
		Assert.assertEquals("{AB}", Amigos.resolverLinha("{ABCDE}*{ABCD}*{ABCEFG}*{ABFG}*{AB}"));
	}

	@Test
	public void test11() {
		Assert.assertEquals("{AB}", Amigos.resolverLinha("{A}+({B})"));
	}

	@Test
	public void test12() {
		Assert.assertEquals("{ABCE}", Amigos.resolverLinha("{ABC}+{CDE}*{CEZ}"));
	}

	@Test
	public void test13() {
		Assert.assertEquals("{ABC}", Amigos.resolverLinha("{ABC}+{CDE}*{CEZ}*{Z}"));
	}

	@Test
	public void test14() {
		Assert.assertEquals("{}", Amigos.resolverLinha("{}"));
	}

	@Test
	public void test15() {
		Assert.assertEquals("{}", Amigos.resolverLinha("{}+{}"));
	}

	@Test
	public void test16() {
		Assert.assertEquals("{}", Amigos.resolverLinha("{}-{}"));
	}

	@Test
	public void test17() {
		Assert.assertEquals("{}", Amigos.resolverLinha("{}*{}"));
	}

	@Test
	public void test18() {
		Assert.assertEquals("{A}", Amigos.resolverLinha("{}*{}+{A}"));
	}

	@Test
	public void test19() {
		Assert.assertEquals("{}", Amigos.resolverLinha("{A}*{A}*{}"));
	}

	@Test
	public void test20() {
		Assert.assertEquals("{B}", Amigos.resolverLinha("{B}*({AC}+{BC})"));
	}

	@Test
	public void test21() {
		Assert.assertEquals("{CE}", Amigos.resolverLinha("({ABC}+{CDE})*{CEZ}"));
	}

	@Test
	public void test22() {
		Assert.assertEquals("{C}", Amigos.resolverLinha("{ABC}*(({A}+{B}+{C})*{C})"));
	}

	@Test
	public void test23() {
		Assert.assertEquals("{ABC}", Amigos.resolverLinha("{ABC}+{}*{AF}*{}+{}"));
	}

	@Test
	public void test24() {
		Assert.assertEquals("{BC}", Amigos.resolverLinha("{BC}+({A}+({C}+{B}))-{A}+{}-{}"));
	}

	@Test
	public void test25() {
		Assert.assertEquals("{BC}", Amigos.resolverLinha("({})+{BC}+({A}+({C}+{B}))-{A}+{}-{}"));
	}

	@Test
	public void test26() {
		Assert.assertEquals("{}", Amigos.resolverLinha("({})"));
	}

	@Test
	public void test27() {
		Assert.assertEquals("{CDEFGIJKLMNOPQRSTUVWXYZ}", Amigos.resolverLinha("({CDEFGIJKLMNOPQRSTUVWXYZ}-{}+{F})+({ABCDE}-{H}-{A}-{B})"));
	}

	@Test
	public void test28() {
		Assert.assertEquals("{ABCDEFGHIJKLMNOPQRSTUVWXYZ}", Amigos.resolverLinha("{ABCDEFGHIJKLMNOPQRSTUVWXYZ}"));
	}

	@Test
	public void test29() {
		Assert.assertEquals("{A}", Amigos.resolverLinha("({A})"));
	}

	@Test
	public void test30() {
		Assert.assertEquals("{ABC}", Amigos.resolverLinha("{AC}+({B}+{C})"));
	}

	@Test
	public void test31() {
		Assert.assertEquals("{ABCDEFGHIJKLMNOPQRSUVWYZ}",
				Amigos.resolverLinha("{ABCDEFGHIJKLMNOPQRSTUVWXYZ}*{ABCDEFGHIJKLMNOPQRSUVWXYZ}*{ABCDEFGHIJKLMNOPQRSTUVWYZ}*{ABCDEFGHIJKLMNOPQRSTUVWXYZ}"));
	}

	@Test
	public void test32() {
		Assert.assertEquals("{BYZ}", Amigos.resolverLinha("{YZ}+{AB}*{BC}"));
	}

	@Test
	public void test33() {
		Assert.assertEquals("{BY}", Amigos.resolverLinha("{YZ}+{AB}*{BC}-{Z}"));
	}

	@Test
	public void test34() {
		Assert.assertEquals("{A}", Amigos.resolverLinha("((({A})))"));
	}

	@Test
	public void test35() {
		Assert.assertEquals("{A}", Amigos.resolverLinha("({ABCD}*(({ABC}*{AB}*{A})))"));
	}

	@Test
	public void test36() {
		Assert.assertEquals("{ABCDEFGIJKLMNOPQRSTUVWXYZ}", Amigos.resolverLinha("(({CDEFGIJKLMNOPQRSTUVWXYZ}-{}+{F})+({ABCDE}-{H}-{A}-{B}))-({A})+({ABCD}-{CZ}+{CZ}*{CZ})"));
	}

	@Test
	public void test37() {
		Assert.assertEquals("{}", Amigos.resolverLinha(""));
	}

	@Test
	public void test38() {
		Assert.assertEquals("{ABCD}", Amigos.resolverLinha("(({AB})+({CD}))"));
	}

}
