window.ReadSpeaker||(window.ReadSpeaker={});window.ReadSpeaker.DocReader||(window.ReadSpeaker.DocReader={});
window.ReadSpeaker.DocReader.AutoAdd=function(){var h=!1,b={cid:"bwgmg",doc_list:"\\.doc \\.docx \\.odt \\.pdf \\.rtf \\.ppt \\.pptx \\.xls \\.xlsx \\.epub type=drlink".split(" "),img_href:document.location.protocol+"//media.readspeaker.com/images/buttons/listen_icons/icon_16px.gif",img_alt:"Open this document with ReadSpeaker docReader",lang:"en_us",useHrefLang:!0,drClass:"rspkr_add_drlink"};return{init:function(){if(!1===h){if("object"==typeof window.rsDocReaderConf){var e=window.rsDocReaderConf,
c;c=b;for(var a in e)e.hasOwnProperty(a)&&(c[a]=e[a])}c=document.getElementsByTagName("A");e=[];for(a=0;a<c.length;a++)e[a]=c[a];for(c=0;c<e.length;c++){a:{var f=e[c];a=f.href;var d=f.className;f.innerHTML.toLowerCase();if(a&&1<a.length){f=new RegExp("("+b.doc_list.join("|")+")([^a-zA-Z0-9]|$)","i");if(a.match(f)){a=!0;break a}if(d.match(new RegExp(b.drClass,"i"))){a=!0;break a}}a=!1}if(a&&(a=e[c].href,d="",d=-1<a.indexOf("http")||-1<a.indexOf("https")?a:0==a.indexOf("/")?document.location.protocol+
"://"+document.location.hostname+a:-1==a.indexOf("/")?document.location.protocol+"://"+document.location.hostname+document.location.pathname+a:a,""!==d)){a=e[c];var f=d,g=document.createElement("img");g.setAttribute("src",b.img_href);g.setAttribute("alt",b.img_alt);g.setAttribute("title",b.img_alt);g.style.border="0px";g.style.display="inline";g.style.margin="0px";g.style.position="absolute";g.className="rspkr_dr_img";var l=a.getAttribute("hreflang");l&&""!==l&&!1!==b.useHrefLang||(l=b.lang);var d=
document.createElement("a"),k=document.location.protocol+"//docreader.readspeaker.com/docreader/?cid=REPLACE_CID&lang=REPLACE_LANG&url=REPLACE_URL",k=k.replace("REPLACE_CID",b.cid),k=k.replace("REPLACE_LANG",l),k=k.replace("REPLACE_URL",encodeURIComponent(f));d.setAttribute("href",k);d.setAttribute("onclick","window.open(this.href); return false;");d.style.margin="0 0 0 3px";d.className="rspkr_dr_link";d.onclick=new Function("window.open(this.href); return false;");d.setAttribute("target","_blank");
d.appendChild(g);f=document.createDocumentFragment();f.appendChild(d);a.parentNode.insertBefore(f,a.nextSibling)}}h=!0}},Settings:b}}();
(function(h){var b=navigator.userAgent,e=/*@cc_on!@*/false,c=setTimeout;/webkit/i.test(b)?c(function(){var a=document.readyState;"loaded"==a||"complete"==a?h():c(arguments.callee,10)},10):/mozilla/i.test(b)&&!/(compati)/.test(b)||/opera/i.test(b)?document.addEventListener("DOMContentLoaded",h,!1):e?function(){var a=document.createElement("doc:rdy");try{a.doScroll("left"),h()}catch(b){c(arguments.callee,0)}}():window.onload=h})(window.ReadSpeaker.DocReader.AutoAdd.init);
