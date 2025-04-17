package ra.edu.utils;

public class EnumUtils {
    public static <E extends Enum<E>> E fromString(Class<E> enumClass, String value) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Không hợp lệ " + enumClass.getSimpleName() + ": " + value);
    }
}
