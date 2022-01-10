package co.com.sofka.questions.utilties;

import java.util.Map;
import java.util.Objects;

public class VotacionUtils {

    private VotacionUtils (){
        /*
        * Esta clase se utilizara solo para metodos estaticos por eso se previene su instancia
        * */
    }

    public static Integer contarVotos(Map<String, String> map, Object value) {
        return (int) map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey).count();
    }
}
