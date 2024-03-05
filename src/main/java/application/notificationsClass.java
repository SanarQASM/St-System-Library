package application;

import org.controlsfx.control.Notifications;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;

public class notificationsClass {
	public void showNotificaitonSomethingWrong(String message) {
		Image image2=new Image(Objects.requireNonNull(getClass().getResource("/image/somethingWrong.png")).toString());
		Notifications notification=Notifications.create().title("try again latter")
				.text(message)
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image2));
		notification.show();
	}
	public void showNotificaitonNointernet() {
		Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/no-wifi.png")).toString());
		Notifications notification=Notifications.create().title("No internet Access")
				.text("please check internet before try")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificaitonCheckYourEmail() {
		Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/EmailSentSuccessfully.png")).toString());
		Notifications notification=Notifications.create().title("Email Send Succussfully")
				.text("Check your email")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificationTooManyTry() {
		Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/tooManyTry.png")).toString());
		Notifications notification=Notifications.create().title("Too many try")
				.text("try again later")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificaitonReplayYourEmail() {
		Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/EmailSentSuccessfully.png")).toString());
		Notifications notification=Notifications.create().title("Email sent successfully")
				.text("You will be replied with the same email within a short time")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificaitonPasswordChangeSuccussfuly() {
		Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/passwordChange.png")).toString());
		Notifications notification=Notifications.create().title("Password Change successfully")
				.text("You can use a new password now")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificaitonNewEmailSetSuccussfully() {
		Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/changeEmail.png")).toString());
		Notifications notification=Notifications.create().title("Email Change successfully")
				.text("You can use a new Email now")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificaitonNewUsernameSetSuccussfully() {
		Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/changeUsername.png")).toString());
		Notifications notification=Notifications.create().title("Username Change successfully")
				.text("You can use a new Username now")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificationSendToReview() {
		Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/review.png")).toString());
		Notifications notification=Notifications.create().title("Book added successfully")
				.text("Please wait for the review, we will show it in the \"Additional books after the review\"")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificationFieldEmpty() {
		Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/error.png")).toString());
		Notifications notification=Notifications.create().title("The information is incorrect")
				.text("Look at the data again and add it")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificationInvalideType() {
		Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/invalid.png")).toString());
		Notifications notification=Notifications.create().title("File type is not allowed")
				.text("Please verify the file type and try again")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
	public void showNotificaitonEnterCorrectInromation() {
		Image image=new Image(Objects.requireNonNull(getClass().getResource("/image/incorrect.png")).toString());
		Notifications notification=Notifications.create().title("Wrong Information's")
				.text("Please check the Information")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}

	public void showNotificationEnterEmail() {
		Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/emailMain.png")).toString());
		Notifications notification = Notifications.create().title("Email Address")
				.text("Add your email address")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}

    public void showNotificationsEnterNewPassword() {
		Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/passwordChange.png")).toString());
		Notifications notification = Notifications.create().title("Password")
				.text("Add new and different Password")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
    }

	public void showNotificationsEnternewUsername() {
		Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/review.png")).toString());
		Notifications notification = Notifications.create().title("Username")
				.text("Add new and different Username")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}

	public void shownotificationsPleaseWait() {
		Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/review.png")).toString());
		Notifications notification = Notifications.create().title("Please Wait")
				.text("It may take some time")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}

	public void showNotificationsSuccessfullyCreateAccount() {
		Image image = new Image(Objects.requireNonNull(getClass().getResource("/image/account_created_successfully.png")).toString());
		Notifications notification = Notifications.create().title("account created successfully")
				.text("Now you can log in")
				.darkStyle()
				.hideAfter(Duration.seconds(5))
				.graphic(new ImageView(image));
		notification.show();
	}
}
