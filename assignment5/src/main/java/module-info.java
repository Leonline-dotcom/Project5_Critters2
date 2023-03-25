module assignment5 {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;

    opens assignment5 to javafx.fxml;
    exports assignment5;
}
