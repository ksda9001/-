package com.trinity.service.impl;

import com.commons.entity.Blog;
import com.commons.entity.BlogCombination;
import com.commons.entity.Tag;
import com.commons.entity.Type;
import com.trinity.mapper.BlogMapper;
import com.trinity.mapper.TagMapper;
import com.trinity.mapper.TypeMapper;
import com.trinity.service.BlogCombinationService;
import com.trinity.util.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BlogCombinationServiceImpl implements BlogCombinationService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    TypeMapper typeMapper;


    @Override
    public List<BlogCombination> findBlogCombinationByCondition(Map<String, Object> map) {
        List<Blog> blogs = blogMapper.findBlogByCondition(map);
        return getBlogCombinations(blogs);
    }

    @Override
    public List<BlogCombination> findBlogCombinationByConditionVisible(Map<String, Object> map) {
        map.put("state", 1);
        List<Blog> blogs = blogMapper.findBlogByCondition(map);
        return getBlogCombinations(blogs);
    }

    @Override
    public BlogCombination findBlogCombinationById(Integer blogId) {
        Blog blog = blogMapper.findBlogById(blogId);
        //浏览数+1
        blog.setViews(blog.getViews() + 1);
        blogMapper.updateBlog(blog);
        return getBlogCombination(blog);
    }

    @Override
    public List<BlogCombination> findBlogCombinationAllVisible() {
        List<Blog> blogs = blogMapper.findBlogAllVisible();
        return getBlogCombinations(blogs);
    }

    /**
     * 根据找到的多个blog将其封装成BlogCombination返回
     *
     * @param blogs
     * @return
     */
    private List<BlogCombination> getBlogCombinations(List<Blog> blogs) {
        List<BlogCombination> blogCombinationList = new ArrayList<>();
        for (Blog blog : blogs) {
            Type type = typeMapper.findTypeById(blog.getType());
            List<Tag> tags = tagMapper.findTagByIds(TagUtil.stringTolist(blog.getTags()));
            BlogCombination blogCombination = new BlogCombination(blog, type, tags);
            blogCombinationList.add(blogCombination);
        }
        return blogCombinationList;
    }

    /**
     * 根据找到的单个blog将其封装成BlogCombination返回
     *
     * @param blog
     * @return
     */
    private BlogCombination getBlogCombination(Blog blog) {
        Type type = typeMapper.findTypeById(blog.getType());
        List<Tag> tags = tagMapper.findTagByIds(TagUtil.stringTolist(blog.getTags()));
        BlogCombination blogCombination = new BlogCombination(blog, type, tags);
        return blogCombination;
    }


}
