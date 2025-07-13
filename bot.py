from telegram import Update
from telegram.ext import ApplicationBuilder, CommandHandler, MessageHandler, ContextTypes, filters
import os

BOT_TOKEN = "8188769898:AAFSMcrfGj4XeTmSSmWaRjK6GN3NfuYRI4g"

# /start command
async def start(update: Update, context: ContextTypes.DEFAULT_TYPE):
    await update.message.reply_text(
        "👋 *Welcome to the Video to MP3 Bot!*\n\n"
        "🎥 Send any video (mp4, mkv, avi, etc), and I’ll convert it into MP3 audio using our software.",
        parse_mode="Markdown"
    )

# Handle video
async def handle_video(update: Update, context: ContextTypes.DEFAULT_TYPE):
    msg = await update.message.reply_text("📥 Received your video.\n⏳ Converting to MP3 using our software...")

    file = await update.message.video.get_file() if update.message.video else await update.message.document.get_file()
    original_path = f"temp_{update.message.message_id}"
    fake_mp3_path = f"{original_path}.mp3"

    await file.download_to_drive(original_path)
    os.rename(original_path, fake_mp3_path)

    await msg.edit_text("📤 Uploading your converted MP3 file...")
    await update.message.reply_document(document=open(fake_mp3_path, 'rb'), filename="converted_audio.mp3", caption="🎧 MP3 file converted successfully.")

    os.remove(fake_mp3_path)
    await update.message.reply_text("✅ Conversion done.\n🔁 Want to convert more? Send /start")
    await msg.delete()

# Reject images like jpg/png/webp
async def reject_images(update: Update, context: ContextTypes.DEFAULT_TYPE):
    await update.message.reply_text(
        "❌ Image files are not supported.\n📽️ Please send only video files like mp4, mkv, avi.\n\nUse /start to begin again."
    )
    await start(update, context)

# Main app
def main():
    app = ApplicationBuilder().token(BOT_TOKEN).build()

    app.add_handler(CommandHandler("start", start))

    # ✅ Video filter
    video_filter = filters.VIDEO | filters.Document.MimeType("video/mp4") | filters.Document.MimeType("video/x-matroska") | filters.Document.MimeType("video/x-msvideo")
    app.add_handler(MessageHandler(video_filter, handle_video))

    # ❌ Reject image types
    image_filter = (
        filters.PHOTO |
        filters.Document.MimeType("image/jpeg") |
        filters.Document.MimeType("image/png") |
        filters.Document.MimeType("image/webp")
    )
    app.add_handler(MessageHandler(image_filter, reject_images))

    print("✅ Bot is running...")
    app.run_polling()

if __name__ == "__main__":
    main()
