import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class Main {
    public static void main(String[] args) {
        TelegramBot bot = new TelegramBot("1091538928:AAHJeExo2EX3C66-jS4Ew3I6GfBNDyj_teo");
        bot.setUpdatesListener(updates -> {
            for (Update update: updates) {
                if (update.message().text().equals("/start")) continue;

                long chatId = update.message().chat().id();

                String[] strings = update.message().text().split(" ");

                URLConnectionReader urlReader = new URLConnectionReader(strings[0], strings[1], weather -> {

                    String stringBuilder = "Погода в городе " + strings[0] + " в стране " + strings[1] + "\n" +
                            "Температура: " + weather.getTemperature() + "°C\n" +
                            "Облачность: " + weather.getClouds() + "\n" +
                            "Скорость ветра: " + weather.getWindSpeed() + " м/с\n" +
                            "Влажность: " + weather.getHumidity() + "%";

                    bot.execute(new SendMessage(chatId, stringBuilder));
                });

                urlReader.start();
            }

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
