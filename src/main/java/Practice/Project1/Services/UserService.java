package Practice.Project1.Services;

import Practice.Project1.entity.User;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final DynamoDBMapper dynamoDBMapper;

    public UserService(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void saveUser(User user) {
        dynamoDBMapper.save(user);
    }

    public User getUser(String userId) {
        return dynamoDBMapper.load(User.class, userId);
    }

    public List<User> getAllUsers(){
        return dynamoDBMapper.scan(User.class, new DynamoDBScanExpression());
    }

    public String deleteUser(String id){
        User user=getUser(id);
        if(user==null){
            throw new RuntimeException("No user found with the given ID");
        }else{
            dynamoDBMapper.delete(user);
            return "user deleted Successfully";
        }
    }

    public User updateUser(User newUser) {
        User user = getUser(newUser.getUserId());
        if (user == null) {
            return new User();
        }
        else{
            if(newUser.getName()!=null){user.setName(newUser.getName());}
            if(newUser.getDesignation()!=null) {user.setDesignation(newUser.getDesignation());}
            dynamoDBMapper.save(user);
            return user;
        }
    }
}