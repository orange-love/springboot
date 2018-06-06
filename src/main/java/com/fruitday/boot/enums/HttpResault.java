package com.fruitday.boot.enums;

public enum HttpResault {
    NOT_FOUND(404, "资源无法找到异常"),
    ERROR(1, "其他错误"),
    PARAMETER_ERROR(2, "参数错误");

    private final int value;
    private final String reasonPhrase;

    private HttpResault(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public boolean is1xxInformational() {
        return HttpResault.Series.INFORMATIONAL.equals(this.series());
    }

    public boolean is2xxSuccessful() {
        return HttpResault.Series.SUCCESSFUL.equals(this.series());
    }

    public boolean is3xxRedirection() {
        return HttpResault.Series.REDIRECTION.equals(this.series());
    }

    public boolean is4xxClientError() {
        return HttpResault.Series.CLIENT_ERROR.equals(this.series());
    }

    public boolean is5xxServerError() {
        return HttpResault.Series.SERVER_ERROR.equals(this.series());
    }

    public HttpResault.Series series() {
        return HttpResault.Series.valueOf(this);
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    public static HttpResault valueOf(int statusCode) {
        HttpResault[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            HttpResault status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }

    public static enum Series {
        INFORMATIONAL(1),
        SUCCESSFUL(2),
        REDIRECTION(3),
        CLIENT_ERROR(4),
        SERVER_ERROR(5);

        private final int value;

        private Series(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }

        public static HttpResault.Series valueOf(int status) {
            int seriesCode = status / 100;
            HttpResault.Series[] var2 = values();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                HttpResault.Series series = var2[var4];
                if (series.value == seriesCode) {
                    return series;
                }
            }

            throw new IllegalArgumentException("No matching constant for [" + status + "]");
        }

        public static HttpResault.Series valueOf(HttpResault status) {
            return valueOf(status.value);
        }
    }
}
