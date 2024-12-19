package com.app.springpowpow.mapper;

import com.app.springpowpow.domain.PostFileVO;
import com.app.springpowpow.domain.ProductFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostFileMapper {
    //    사진 추가
    public void insertImage(PostFileVO postFileVO);

    //    SELECT ALL
    public List<PostFileVO> selectAllImage();

    //    사진 수정
    public void updateImage(PostFileVO postFileVO);


    //    게시글 삭제시 사진도 삭제
    public void deleteAllImage(Long postId);
}
