package com.suggestion.service.service;

import com.suggestion.service.model.entities.AccountEntity;
import com.suggestion.service.model.request.BodyLogin;
import com.suggestion.service.model.request.BodyRegiter;
import com.suggestion.service.model.response.Result;
import com.suggestion.service.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoginService {
    @Autowired
    private AccountRepo mAccountRepo;

    public Result login(BodyLogin bodyLogin){
        AccountEntity account = mAccountRepo.findByEmail(bodyLogin.getEmail());
        if(account==null){
            Result result = new Result(2,"Tài khoản này không tồn tại",null);
            return result;
        }else {
            if(!account.getPassword().equals(bodyLogin.getPassword())){
                Result result = new Result(3,"Mật khẩu đăng nhập không đúng",null);
                return result;
            }else {
                Result result = new Result(200,"OK",account);
                return result;
            }
        }
    }

    public Result register(BodyRegiter bodyRegiter){
        AccountEntity account = mAccountRepo.findByEmail(bodyRegiter.getEmail());
        if(account!=null){
            Result result = new Result(1,"Email đã có người sử dụng",null);
            return result;
        }else {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setEmail(bodyRegiter.getEmail());
            accountEntity.setFullname(bodyRegiter.getFullname());
            accountEntity.setPassword(bodyRegiter.getPassword());
            if(bodyRegiter.getRole()==1){
                accountEntity.setRole("ADMIN");
            }else {
                accountEntity.setRole("USER");
            }
            mAccountRepo.save(accountEntity);
            Result result = new Result(200,"OK",accountEntity);
            return result;
        }
    }


}
