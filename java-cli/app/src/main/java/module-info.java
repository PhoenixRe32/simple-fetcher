module com.pittacode.apihelper {
    requires jdk.crypto.ec; // needed for ssl communication
    requires org.slf4j;
    requires java.net.http;
    requires java.sql;
    requires com.google.gson;
    requires org.apache.logging.log4j;

    exports com.pittacode.apihelper;
}