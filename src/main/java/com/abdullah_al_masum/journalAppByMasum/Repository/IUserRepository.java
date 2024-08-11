package com.abdullah_al_masum.journalAppByMasum.Repository;

import com.abdullah_al_masum.journalAppByMasum.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
}
