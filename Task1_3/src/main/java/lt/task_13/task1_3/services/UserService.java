package lt.task_13.task1_3.services;

import lt.task_13.task1_3.exceptions.BadEmailException;
import lt.task_13.task1_3.exceptions.BadPasswordException;
import lt.task_13.task1_3.exceptions.BadPhoneNumberException;
import lt.task_13.task1_3.junittest.emailvalidator.EmailValidator;
import lt.task_13.task1_3.junittest.passwordvalidator.PasswordValidator;
import lt.task_13.task1_3.junittest.phonenumbervalidator.CountryPhoneNumberDesc;
import lt.task_13.task1_3.junittest.phonenumbervalidator.PhoneNumberValidator;
import lt.task_13.task1_3.model.User;
import lt.task_13.task1_3.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
        emailValidator = new EmailValidator();
        passwordValidator = new PasswordValidator(8, "!@#$%^&*()_-=+");
        phoneNumberValidator = new PhoneNumberValidator();

        CountryPhoneNumberDesc ltuPhoneNumber = new CountryPhoneNumberDesc();
        ltuPhoneNumber.setCode("+370");
        ltuPhoneNumber.setTrunkPrefix("8");
        ltuPhoneNumber.setLength(11);
        ltuPhoneNumber.setAlpha3code("ltu");
        phoneNumberValidator.addCountryDesc(ltuPhoneNumber);
    }

    private final UserRepository userRepository;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final PhoneNumberValidator phoneNumberValidator;

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User add(User user) throws BadEmailException, BadPasswordException, BadPhoneNumberException {

        if(!emailValidator.validateEmail(user.getEmail())) {
            throw new BadEmailException();

        } else if(!passwordValidator.validatePassword(user.getPassword())) {
            throw new BadPasswordException();

        } else if(phoneNumberValidator.validateNumber(user.getPhoneNumber(), "ltu") == null) {
            throw new BadPhoneNumberException();

        } else {
            return userRepository.save(user);
        }

    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User update(User user, int id) throws BadEmailException, BadPasswordException, BadPhoneNumberException {
        user.setId(id);
        if(!emailValidator.validateEmail(user.getEmail())) {
            throw new BadEmailException();

        } else if(!passwordValidator.validatePassword(user.getPassword())) {
            throw new BadPasswordException();

        } else if(phoneNumberValidator.validateNumber(user.getPhoneNumber(), "ltu") == null) {
            throw new BadPhoneNumberException();

        } else {
            return userRepository.save(user);
        }
    }
}
