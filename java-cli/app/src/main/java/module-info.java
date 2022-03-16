module com.pittacode.apihelper {
    requires jdk.crypto.ec; // needed for ssl communication
    requires org.slf4j;
    requires java.net.http;
    requires java.sql;
    requires com.google.gson;
    requires json.path;

    exports com.pittacode.apihelper;
    exports com.pittacode.apihelper.file;
}