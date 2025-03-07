module com.example.csc_311_module_04_assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;


    opens com.example.csc_311_module_04_assignment to javafx.fxml;
    exports com.example.csc_311_module_04_assignment;
}