package com.example.demo1.tools.enmu;

public enum UrlEnum {
        公开(0),
        验证(1);

        UrlEnum(Integer type) {
            this.type = type;
        }

        private Integer type;

        public Integer getType() {
            return this.type;
        }
}
