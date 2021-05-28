package com.example.baitap_3;

public class NamePlace {

        private String tenDD;
        private boolean active;

        public NamePlace(String tenDD){
            this.tenDD= tenDD;
            this.active=true;
        }

        public NamePlace(String tenDD,boolean active){
            this.tenDD= tenDD;
            this.active=active;
        }

        public String getTenDD(){
            return tenDD;
        }

        public void setTenDD(String tenDD){
            this.tenDD=tenDD;
        }

        public boolean isActive(){
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String toString() {
            return this.tenDD ;
        }


}
