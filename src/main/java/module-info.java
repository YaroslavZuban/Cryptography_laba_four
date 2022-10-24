module com.example.cryptography_laba_four {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cryptography_laba_four to javafx.fxml;
    exports com.example.cryptography_laba_four;
}