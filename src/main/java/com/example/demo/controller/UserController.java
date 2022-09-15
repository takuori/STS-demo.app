package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.data.entity.User;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.form.UserForm;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public String getUsers(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "users";
	}
	
	@GetMapping("/newuser") 
	//"/newuser"に対してHTTPのGETメソッドを受け付けるための設定
	
    public String getNewUser(Model model) {
		//引数で受け取ったModelのインスタンスに、35行目で新しくインスタンス化したUserFormを渡す
		//これにより、HTMLテンプレートから値をセットするための設定ができた
		
		UserForm userForm = new UserForm();
		model.addAttribute("userForm", userForm);
    	return "newuser";
    }
	
	@PostMapping("/newuser")
	//引数のuserFormに@Validatedアノテーションを追加することで、registerUser()メソッドが呼ばれる前に、
	//springFrameworkの中でUserFormクラスのフォールドに付与されたアノテーションを元にバリデーションを実施してくれる。
	public String registerUser(@Validated UserForm userForm, BindingResult bindingResult) {
		//引数にBindingResultを追加することで、UserFormクラスのフィールドに対するバリデーションの結果を保持している
		if (bindingResult.hasErrors()) {
			return "newuser";
			//引数で受け取ったBindingResultには様々なメソッドが用意されている
			//その中のhasErrorsメソッドは、もし一つでもバリデーションエラーがあればtrueを返す
			//trueの場合は、ユーザー登録処理を実行せず即座に登録画面に返す処理をしている
		}
		User user = new User();
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		
		userRepository.save(user);
		return "redirect:/users";
	}

}
