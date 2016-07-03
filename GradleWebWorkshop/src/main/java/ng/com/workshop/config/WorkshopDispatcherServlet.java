package ng.com.workshop.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class WorkshopDispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }


    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    // @Override
    // protected void customizeRegistration(Dynamic registration) {
    // String location = "/tmp/worktools/uploads";
    // long maxFileSize = 2097152; // 2mb
    // long maxRequestSize = 4194304; // 4mb
    // int fileSizeThreshold = 0; // means all files will be written
    // registration.setMultipartConfig(new MultipartConfigElement(location, maxFileSize, maxRequestSize,
    // fileSizeThreshold));
    // }
}
