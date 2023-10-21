module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vsu.cs.zagorodnev_g_a to javafx.fxml;
    exports ru.vsu.cs.zagorodnev_g_a;
}