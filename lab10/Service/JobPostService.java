package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getJobPosts(){
        return jobPostRepository.findAll();
    }
    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }
    public Boolean updatePost(Integer id,JobPost jobPost){
        JobPost oldjobPost=jobPostRepository.getById(id);
        if(oldjobPost==null){
            return false;
        }
        oldjobPost.setTitle(jobPost.getTitle());
        oldjobPost.setDescription(jobPost.getDescription());
        oldjobPost.setSalary(jobPost.getSalary());
        oldjobPost.setLocation(jobPost.getLocation());
        oldjobPost.setPostingDate(jobPost.getPostingDate());
        jobPostRepository.save(oldjobPost);
        return true;
    }
    public Boolean deletePost(Integer id){
        JobPost post=jobPostRepository.getById(id);
        if(post==null){
            return false;
        }
        jobPostRepository.delete(post);
        return true;
    }
}
