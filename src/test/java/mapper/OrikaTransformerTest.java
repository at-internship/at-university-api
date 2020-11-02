package mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import com.agilethought.internship.university.domain.CourseResponse;
import com.agilethought.internship.university.model.Course;
import com.agilethought.internship.university.service.common.OrikaTransformer;
import com.agilethought.internship.university.service.common.TestUtils;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;

import javax.persistence.Index;

public class OrikaTransformerTest {

    @InjectMocks
    private OrikaTransformer orikaTransformer;

    @Mock
    private MapperFacade mapperFacade;
    private TestUtils testUtils;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        testUtils = new TestUtils();
        orikaTransformer.setMapperFacade(new DefaultMapperFactory.Builder().build());
    }

    @Test
    public void testCourseTransformer(){
        doReturn(testUtils.getEmptyCourse()).when(mapperFacade).map(TestUtils.getEmptyCreateCourseRequest(), Course.class);
        assertEquals(testUtils.getEmptyCourse(),orikaTransformer.transformer(TestUtils.getEmptyCreateCourseRequest()));
    }

    @Test
    public void testCourseResponseTransformer(){
        doReturn(TestUtils.getEmptyCourseResponse()).when(mapperFacade).map(testUtils.getEmptyCourse(), CourseResponse.class);
        assertEquals(TestUtils.getEmptyCourseResponse(),orikaTransformer.transformer(testUtils.getEmptyCourse()));
    }

}
