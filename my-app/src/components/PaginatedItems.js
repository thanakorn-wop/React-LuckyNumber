import React, { useEffect, useState } from "react";
import ReactPaginate from 'react-paginate';
function PaginatedItems(props)
{
      const item = props.items;
    //   const [currentItem,setCurrentItem] = useState([])
    //   const [ pageCount,setPageCount] = useState(0)
    //   const [itemOffset,setItemOffset] = useState(0)
    //   const itemPerPage = 10;
    const [currentPage,setCurrentPage] = useState(0);
    const [postsPerPage] = useState(10);
    const [pageNumberLimit, setpageNumberLimit] = useState(5);
    const [pageNumber,setPageNumber] = useState([])
    //  console.log("dataset = ",item)
    
    useEffect(()=>{
        const indexOfLastPost = currentPage + postsPerPage;
        // const indexOfFirstPost = indexOfLastPost - postsPerPage;
        const pageNumber =[];
            for(let i = 1; i<=Math.ceil(item.length / postsPerPage); i++)
            {
                setPageNumber([...pageNumber,i])
            }
        let currentPosts = item.slice(currentPage,indexOfLastPost);
        console.log("check post item = ",item)
        console.log("check post currentPage indexOfLastPost = ",currentPage,indexOfLastPost)
        console.log("check post data = ",currentPosts)
        const paginate = pageNumber =>setCurrentPage(pageNumber)
        // const endOffset = itemOffset +itemPerPage;
        // setCurrentItem(item.slice(itemOffset,endOffset));
        // setPageCount(Math.ceil(item.length / itemPerPage));
      //  console.log("useEffect again =",currentPosts)
        props.callBackItem(currentPosts)



    },[currentPage,postsPerPage,item])

    //console.log("item = ",item)
    const handlePageClick = (data)=>{
       // console.log("click = ",item)
        const newOffset = (data.selected * postsPerPage) % item.length ;
    //    console.log("newOffset = ",newOffset)
        setCurrentPage(newOffset);
    }
    return(
        <div className="paginate" style={{"position":"relative","zIndex":"1","margin":"0 auto"}}>
            {/* {
                currentItem.map((data,index)=>{
                    console.log("check data = ",index)
                })
            } */}
        <ReactPaginate 
        previousLabel ={"previous"}
        nextLabel = {"Next"}
        breakLabel ={"..."}
        pageCount={pageNumber}
        onPageChange={handlePageClick}
        containerClassName={"pagination"}
        pageClassName={"page-item"}
        pageLinkClassName={"page-link"}
        previousClassName={"page-item"}
        previousLinkClassName={"page-link"}
        nextClassName={"page-item"}
        nextLinkClassName={"page-link"}
        activeClassName={"active"}
        />
        </div>
       

    );
}
export default PaginatedItems;