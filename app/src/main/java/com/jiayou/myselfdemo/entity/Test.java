package com.jiayou.myselfdemo.entity;

/**
 * Created by Malik J on 2018/1/8.
 */

public class Test {

    /**
     * SERVICE : {"SERVICE_BODY":{"RESPONSE":{}},"SERVICE_HEADER":{"ACQ_ID":"10000000","CHANNEL_ID":"BANK","MAC":"","OP_ID":"","ORG":"000000000001","REQUEST_TIME":"20180108162852","SERVICESN":"APP2018010816285263674","SERVICE_ID":"loanApply","SERV_RESPONSE":{"CODE":"1043","DESC":"字段格式错误,字段名称{CUST_ID},字段为空,APPLY_FORM_ID:20180108162852","STATUS":"F"},"SUB_TERMINAL_TYPE":"WEB","VERSION_ID":"01"}}
     */

    private SERVICEBean SERVICE;

    public SERVICEBean getSERVICE() {
        return SERVICE;
    }

    public void setSERVICE(SERVICEBean SERVICE) {
        this.SERVICE = SERVICE;
    }

    public static class SERVICEBean {
        /**
         * SERVICE_BODY : {"RESPONSE":{}}
         * SERVICE_HEADER : {"ACQ_ID":"10000000","CHANNEL_ID":"BANK","MAC":"","OP_ID":"","ORG":"000000000001","REQUEST_TIME":"20180108162852","SERVICESN":"APP2018010816285263674","SERVICE_ID":"loanApply","SERV_RESPONSE":{"CODE":"1043","DESC":"字段格式错误,字段名称{CUST_ID},字段为空,APPLY_FORM_ID:20180108162852","STATUS":"F"},"SUB_TERMINAL_TYPE":"WEB","VERSION_ID":"01"}
         */

        private SERVICEBODYBean SERVICE_BODY;
        private SERVICEHEADERBean SERVICE_HEADER;

        public SERVICEBODYBean getSERVICE_BODY() {
            return SERVICE_BODY;
        }

        public void setSERVICE_BODY(SERVICEBODYBean SERVICE_BODY) {
            this.SERVICE_BODY = SERVICE_BODY;
        }

        public SERVICEHEADERBean getSERVICE_HEADER() {
            return SERVICE_HEADER;
        }

        public void setSERVICE_HEADER(SERVICEHEADERBean SERVICE_HEADER) {
            this.SERVICE_HEADER = SERVICE_HEADER;
        }

        public static class SERVICEBODYBean {
            /**
             * RESPONSE : {}
             */

            private RESPONSEBean RESPONSE;

            public RESPONSEBean getRESPONSE() {
                return RESPONSE;
            }

            public void setRESPONSE(RESPONSEBean RESPONSE) {
                this.RESPONSE = RESPONSE;
            }

            public static class RESPONSEBean {
            }
        }

        public static class SERVICEHEADERBean {
            /**
             * ACQ_ID : 10000000
             * CHANNEL_ID : BANK
             * MAC :
             * OP_ID :
             * ORG : 000000000001
             * REQUEST_TIME : 20180108162852
             * SERVICESN : APP2018010816285263674
             * SERVICE_ID : loanApply
             * SERV_RESPONSE : {"CODE":"1043","DESC":"字段格式错误,字段名称{CUST_ID},字段为空,APPLY_FORM_ID:20180108162852","STATUS":"F"}
             * SUB_TERMINAL_TYPE : WEB
             * VERSION_ID : 01
             */

            private String ACQ_ID;
            private String CHANNEL_ID;
            private String MAC;
            private String OP_ID;
            private String ORG;
            private String REQUEST_TIME;
            private String SERVICESN;
            private String SERVICE_ID;
            private SERVRESPONSEBean SERV_RESPONSE;
            private String SUB_TERMINAL_TYPE;
            private String VERSION_ID;

            public String getACQ_ID() {
                return ACQ_ID;
            }

            public void setACQ_ID(String ACQ_ID) {
                this.ACQ_ID = ACQ_ID;
            }

            public String getCHANNEL_ID() {
                return CHANNEL_ID;
            }

            public void setCHANNEL_ID(String CHANNEL_ID) {
                this.CHANNEL_ID = CHANNEL_ID;
            }

            public String getMAC() {
                return MAC;
            }

            public void setMAC(String MAC) {
                this.MAC = MAC;
            }

            public String getOP_ID() {
                return OP_ID;
            }

            public void setOP_ID(String OP_ID) {
                this.OP_ID = OP_ID;
            }

            public String getORG() {
                return ORG;
            }

            public void setORG(String ORG) {
                this.ORG = ORG;
            }

            public String getREQUEST_TIME() {
                return REQUEST_TIME;
            }

            public void setREQUEST_TIME(String REQUEST_TIME) {
                this.REQUEST_TIME = REQUEST_TIME;
            }

            public String getSERVICESN() {
                return SERVICESN;
            }

            public void setSERVICESN(String SERVICESN) {
                this.SERVICESN = SERVICESN;
            }

            public String getSERVICE_ID() {
                return SERVICE_ID;
            }

            public void setSERVICE_ID(String SERVICE_ID) {
                this.SERVICE_ID = SERVICE_ID;
            }

            public SERVRESPONSEBean getSERV_RESPONSE() {
                return SERV_RESPONSE;
            }

            public void setSERV_RESPONSE(SERVRESPONSEBean SERV_RESPONSE) {
                this.SERV_RESPONSE = SERV_RESPONSE;
            }

            public String getSUB_TERMINAL_TYPE() {
                return SUB_TERMINAL_TYPE;
            }

            public void setSUB_TERMINAL_TYPE(String SUB_TERMINAL_TYPE) {
                this.SUB_TERMINAL_TYPE = SUB_TERMINAL_TYPE;
            }

            public String getVERSION_ID() {
                return VERSION_ID;
            }

            public void setVERSION_ID(String VERSION_ID) {
                this.VERSION_ID = VERSION_ID;
            }

            public static class SERVRESPONSEBean {
                /**
                 * CODE : 1043
                 * DESC : 字段格式错误,字段名称{CUST_ID},字段为空,APPLY_FORM_ID:20180108162852
                 * STATUS : F
                 */

                private String CODE;
                private String DESC;
                private String STATUS;

                public String getCODE() {
                    return CODE;
                }

                public void setCODE(String CODE) {
                    this.CODE = CODE;
                }

                public String getDESC() {
                    return DESC;
                }

                public void setDESC(String DESC) {
                    this.DESC = DESC;
                }

                public String getSTATUS() {
                    return STATUS;
                }

                public void setSTATUS(String STATUS) {
                    this.STATUS = STATUS;
                }
            }
        }
    }
}
