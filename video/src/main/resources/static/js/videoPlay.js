var path = document.getElementById("path").value;
var titletext = document.getElementById("title").value;
var pic = document.getElementById("pic").value;
var art = new Artplayer({
    container: '.artplayer-app',
    title: titletext,
    poster: pic,
    volume: 0.5,
    isLive: false,
    muted: false,
    autoplay: false,
    pip: true,
    autoMini: true,
    screenshot: true,
    setting: true,
    loop: true,
    flip: true,
    playbackRate: true,
    aspectRatio: true,
    fullscreen: true,
    fullscreenWeb: true,
    miniProgressBar: true,
    mutex: true,
    playsInline: true,
    autoPlayback: false,
    airplay: true,
    theme: "#23ade5",
    lang: navigator.language.toLowerCase(),
});

setTimeout(() => {
    art.url = path;
}, 1000);