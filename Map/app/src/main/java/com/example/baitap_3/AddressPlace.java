package com.example.baitap_3;

public class AddressPlace {

        private String diaChi;

        public AddressPlace(String diaChi){
            this.diaChi= diaChi;
        }

        public String getTenDC(){
            return diaChi;
        }

        public void setTenDC(String diaChi){
            this.diaChi=diaChi;
        }

        public String toString() {

            return this.diaChi ;
        }




}
