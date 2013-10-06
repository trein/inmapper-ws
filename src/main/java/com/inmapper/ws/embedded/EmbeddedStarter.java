package com.inmapper.ws.embedded;

public class EmbeddedStarter {
    
    public static final String CONTEXT_PATH = "/"; //$NON-NLS-1$
    public static final String BASE_DIR = "src/main/webapp"; //$NON-NLS-1$
    
    public static void main(String[] args) throws Exception {
        TomcatEmbeddedServer server = new TomcatEmbeddedServer();
        
        server.setPort(TomcatEmbeddedServer.DEFAULT_PORT);
        server.setBaseDir(TomcatEmbeddedServer.DEFAULT_BASE_DIR);
        server.addContext(CONTEXT_PATH, BASE_DIR);
        server.setup();
        server.start();
        server.await();
    }
}
