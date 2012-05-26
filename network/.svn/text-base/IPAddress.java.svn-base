package hawkge.network;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Holds an ipadress
 * @author computerbeheerder547
 */
public class IPAddress implements Serializable{

    private byte[] ip;

    public IPAddress(int ip1, int ip2, int ip3, int ip4) {
        ip = new byte[] { (byte) (ip1 - 128), (byte) (ip2 - 128), (byte) (ip3 - 128), (byte) (ip4 - 128) };
    }

    public IPAddress(int ip) {
        this.ip = new byte[4];
        this.ip[0] = (byte) ((ip >> 24)%256 - 128);
        this.ip[1] = (byte) ((ip >> 16)%256 - 128);
        this.ip[2] = (byte) ((ip >> 8)%256 - 128);
        this.ip[3] = (byte) ((ip)%256 - 128);
    }

    public IPAddress(String ip) {
        this.ip = new byte[4];
        String[] splitted = ip.split("\\.");
        if(splitted.length != 4) return;
        for(int i = 0; i < 4; i++) {
            this.ip[i] = (byte) (Integer.parseInt(splitted[i]) - 128);
        }
    }
    
    /**
     * Convert the ip to a String.
     * @return String of the ipaddress
     */
    @Override
    public String toString() {
        String r = "" + ((int) ip[0] + 128);
        for(int i = 1; i < 4; i++) {
            r = r + "." + ((int) ip[i] + 128);
        }
        return r;
    }

    public int toInt() {
        return (((int) ip[0] + 128) << 24) +
        (((int) ip[1] + 128) << 16) +
        (((int) ip[2] + 128) << 8) +
        ((int) ip[3] + 128);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IPAddress other = (IPAddress) obj;
        if (!Arrays.equals(this.ip, other.ip)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Arrays.hashCode(this.ip);
        return hash;
    }

}
