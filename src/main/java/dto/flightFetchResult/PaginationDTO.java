/*
 * Gruppe 3 SYS
 */
package dto.flightFetchResult;

/**
 *
 * @author magda
 */
public class PaginationDTO {
    int limit;
    int offset;
    int count;
    int total;

    public PaginationDTO(int limit, int offset, int count, int total) {
        this.limit = limit;
        this.offset = offset;
        this.count = count;
        this.total = total;
    }
    
    

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return total;
    }
    
    
}
