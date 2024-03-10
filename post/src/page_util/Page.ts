import { Direction } from './Direction';
class Page {
     private page: number;
     private limit: number;
     private sorting: string | null = null;
     private ordering: string | null = this.sorting ? 'asc' : null;
     private that = this;

     public build(): Page {
          return this.that;
     }

     // init data
     public pageBy(parameterByPageNumber: number): Pagination {
          this.that.setPage(parameterByPageNumber);
          return this;
     }
     public limitBy(parameterByPageLimit: number): Pagination {
          this.that.setLimit(parameterByPageLimit);
          return this;
     }

     public sortBy(sort: Array<String>) {
          this.that.setSort(sort);
          return this;
     }

     // set private data 
     private setPage(page: number) {
          this.page = page;
     }
     private setLimit(limit: number) {
          this.limit = limit;
     }
     private setSort(sort: Array<String>) {
          
     }
     private setOrder(direction : Direction){
          this.
     }

}
