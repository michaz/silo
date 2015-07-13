package com.pb.sawdust.model.models.provider;

import com.pb.sawdust.util.test.TestBase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The {@code IdDataTest} ...
 *
 * @author crf <br/>
 *         Started Sep 25, 2010 7:52:12 AM
 */
public abstract class IdDataTest extends TestBase {
    protected int id;
    protected IdData idData;

    abstract protected IdData getIdData(int id);

    protected int getId() {
        return random.nextInt(10,15); //in case some others need to be preinitialized
    }

    @Before
    public void beforeTest() {
        id = getId();
        idData = getIdData(id);
    }

    @Test
    public void testGetId() {
        assertEquals(id,idData.getDataId());
    }

}