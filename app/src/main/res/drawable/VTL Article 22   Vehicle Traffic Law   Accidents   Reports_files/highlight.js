function catcherror(){return!0}function QueryString(e){for(var t=null,r=0;r<QueryString.keys.length;r++)if(QueryString.keys[r]==e){t=QueryString.values[r];break}return t}function QueryString_Parse(){for(var e=window.location.search.substring(1),t=e.split("&"),r=0;r<t.length;r++){var n=t[r].indexOf("=");if(n>=0){var i=t[r].substring(0,n),o=t[r].substring(n+1);QueryString.keys[QueryString.keys.length]=i,QueryString.values[QueryString.values.length]=o}}}function getElement(e){return document.getElementById?document.getElementById(e):document.all?document.all[e]:void 0}function findPosY(e){var t=0;if(e.offsetParent)for(;e.offsetParent;)t+=e.offsetTop,e=e.offsetParent;else e.y&&(t+=e.y);return t}function ZRetrieveQuery(){var e,t,r=0;if(e=QueryString("zoom_highlight"),""==e||null==e){if(e=QueryString("zoom_highlightsub"),""==e||null==e)return!1;r=1}e=document.charset&&"utf-8"==document.charset||document.characterSet&&"UTF-8"==document.characterSet?decodeURIComponent(e):unescape(e),e=e.toLowerCase();var n=/\"(.*?)\"|[^\\+\"]+/g;t=e.match(n);for(var i=0;i<t.length;i++)if(""!=t[i]){if(-1!=t[i].indexOf('"')?(t[i]=t[i].replace(/\"/g,""),t[i]=t[i].replace(/\+/g," ")):t[i]=t[i].replace(/\+/g,""),-1!=t[i].indexOf("*")||-1!=t[i].indexOf("?")){t[i]=t[i].replace(/\\/g," "),t[i]=t[i].replace(/\^/g," "),t[i]=t[i].replace(/\#/g," "),t[i]=t[i].replace(/\$/g," "),t[i]=t[i].replace(/\./g," ");var o=/\w/;o.test(t[i])?(t[i]=t[i].replace(/\*/g,"[^\\s]*"),t[i]=t[i].replace(/\?/g,"[^\\s]")):t[i]=""}if(""!=t[i])if(0==r)t[i]="(>[\\s]*|>[^<]+[\\b\\W])("+t[i]+")(<|[\\b\\W][^>]*<)";else{var l="";"[^\\s]*"==t[i].substr(0,7)&&(l="\\b"),t[i]="(>|>[^<]+)"+l+"("+t[i]+")([^>]*<)"}}return t}function ZHighlightText(terms,text){text=text.replace(/&amp;/gi,"&"),text=text.replace(/&nbsp;/gi,"\b"),text=text.replace(/</gi,"&lt;"),text=text.replace(/>/gi,"&gt;");for(var i=0;i<terms.length;i++)if(""!=terms[i]){var l=0;re=new RegExp(terms[i],"gi");var count=0;text=">"+text+"<";do l=text.length,text=text.replace(re,'$1<span class="highlight" id="highlight" name="highlight">$2</span id="highlight">$3'),count++;while(l!=text.length&&100>count);text=text.substring(1,text.length-1)}return text=text.replace(eval("//g"),""),text=text.replace(eval("/\b/g"),"&nbsp;")}function jumpHL(){var e=getElement("highlight");if(e)if(e.scrollIntoView)e.scrollIntoView();else{var t=findPosY(e);100>t?window.scrollTo(0,0):window.scrollTo(0,t-50)}}function ZHighlightReplace(e,t){var r=t.nodeValue,n=ZHighlightText(e,r);if(n!=r){var i=document.createElement("span");i.innerHTML=n,t.parentNode.replaceChild(i,t)}}function ZHighlightSearch(e,t){t||(t=document.body.childNodes);for(var r=0,n=t.length;n>r;r++)ZHighlightSearch(e,t[r].childNodes),SkipZoomStops&&8===t[r].nodeType&&("ZOOMSTOP"==t[r].nodeValue?IsZoomStop=1:"ZOOMRESTART"==t[r].nodeValue&&(IsZoomStop=0)),0==IsZoomStop&&3===t[r].nodeType&&ZHighlightReplace(e,t[r])}function highlight(){if("".match&&document.body){var e=ZRetrieveQuery();0!=e&&(IsZoomStop=0,ZHighlightSearch(e),JumpToFirstOccurance&&jumpHL())}}var JumpToFirstOccurance=!0,CatchJSErrors=!0,SkipZoomStops=!0,IsZoomStop=0;CatchJSErrors&&(window.onerror=catcherror),QueryString.keys=new Array,QueryString.values=new Array,QueryString_Parse();