package ra.edu.datatype;

public enum StatusEnrollment {
    WAITING, DENIED, CANCEL, CONFIRM;

    public String toVietnamese() {
        return switch (this) {
            case WAITING -> "Đang chờ";
            case DENIED -> "Từ chối";
            case CANCEL -> "Hủy";
            case CONFIRM -> "Xác nhận";
        };
    }
}
