package capstone;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.python.util.PythonInterpreter;

/**
 * FXML Controller class
 *
 * @author Tyler Goodfred
 */
public class AdminMainMenuController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Test of mobile stuff
        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
      pyInterp.exec("print('Hello Python World!')");
    }
    }    
    
}
