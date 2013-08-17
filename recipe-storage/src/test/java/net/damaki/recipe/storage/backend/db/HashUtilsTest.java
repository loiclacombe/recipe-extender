package net.damaki.recipe.storage.backend.db;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 22:11
 */
public class HashUtilsTest {
    private HashUtils hashUtils;

    @Before
    public void setUp() throws Exception {
        hashUtils = new HashUtils();
    }

    @org.junit.Test
    public void testGetHex() throws Exception {
        assertEquals("DEADBEEF", hashUtils.getHex(new byte[]{(byte) 0xde, (byte) 0xad, (byte) 0xbe, (byte) 0xef}));
    }

    @org.junit.Test
    public void testComputeHash() throws Exception {
        assertEquals("0B9C2625DC21EF05F6AD4DDF47C5F203837AA32C", hashUtils.computeHash("toto"));
    }
}
