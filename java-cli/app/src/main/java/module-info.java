module com.pittacode.apihelper {
    requires jdk.crypto.ec; // needed for ssl communication
    requires org.slf4j;
    requires java.net.http;
    requires java.sql;
    requires com.google.gson;
    requires org.apache.logging.log4j;
    requires info.picocli;

    exports com.pittacode.apihelper;
    exports com.pittacode.apihelper.converter;

    opens com.pittacode.apihelper to info.picocli;
}