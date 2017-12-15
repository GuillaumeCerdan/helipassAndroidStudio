package agency.getup.helipass;

/**
 * Created by gcerd on 13/12/2017.
 */

public class Offers {

        private int id;

        private double price;

        private String name;

        private double latitude;

        private double longitude;


        public int getId() {
            return id;
        }


        public void setId(int id) {
            this.id = id;
        }


        public double getPrice() {
            return price;
        }


        public void setPrice(double price) {
            this.price = price;
        }


        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public double getLatitude() {
            return latitude;
        }


        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }


        public double getLongitude() {
            return longitude;
        }


        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }


}
