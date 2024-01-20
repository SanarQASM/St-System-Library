package application;

import org.controlsfx.control.Notifications;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class notificationsClass {
	public void showNotificaitonSomethingWrong() {
		Image image2=new Image(getClass().getResource("/image/somethingWrong.png").toString());
		Notifications notification=Notifications.create().title("try again latter")
				.text("something wrong")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image2));
		notification.show();
	}
	public void showNotificaitonNointernet() {
		Image image=new Image(getClass().getResource("/image/no-wifi.png").toString());
		Notifications notification=Notifications.create().title("No internet Access")
				.text("please check internet before try")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificaitonCheckYourEmail() {
		Image image=new Image(getClass().getResource("/image/EmailSentSuccessfully.png").toString());
		Notifications notification=Notifications.create().title("Email Send Succussfully")
				.text("Check your email")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificationTooManyTry() {
		Image image=new Image(getClass().getResource("/image/tooManyTry.png").toString());
		Notifications notification=Notifications.create().title("Too many try")
				.text("try again later")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificaitonReplayYourEmail() {
		Image image=new Image(getClass().getResource("/image/EmailSentSuccessfully.png").toString());
		Notifications notification=Notifications.create().title("Email sent successfully")
				.text("You will be replied with the same email within a short time")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificaitonPasswordChangeSuccussfuly() {
		Image image=new Image(getClass().getResource("/image/passwordChange.png").toString());
		Notifications notification=Notifications.create().title("Password Change successfully")
				.text("You can use a new password now")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificaitonNewEmailSetSuccussfully() {
		Image image=new Image(getClass().getResource("/image/changeEmail.png").toString());
		Notifications notification=Notifications.create().title("Email Change successfully")
				.text("You can use a new Email now")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificaitonNewUsernameSetSuccussfully() {
		Image image=new Image(getClass().getResource("/image/changeUsername.png").toString());
		Notifications notification=Notifications.create().title("Username Change successfully")
				.text("You can use a new Username now")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	
}
