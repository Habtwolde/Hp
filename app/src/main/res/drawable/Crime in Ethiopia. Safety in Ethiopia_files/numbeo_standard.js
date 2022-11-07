function checkSelectedSomeText(idOfElement) {
  element = document.getElementById(idOfElement);
  if (element != null) {
    if (element.value.length > 0) {
      return true;
    }
  }
  return false;
}

function checkSelectedSomeTextNotDashed(idOfElement) {
  element = document.getElementById(idOfElement);
  if (element != null) {
    if (element.value.length > 0 && element.value != '---') {
      return true;
    }
  }
  return false;
}

function enableIfElementsHaveSomeValueOld(resultName, el1name, el2name) {
  el1 = document.getElementById(el1name);
  el2 = document.getElementById(el2name);
  resultButton = document.getElementById(resultName);
  // by default it's disabled. It's enabled if certain special condition is met
  resultButton.disabled = true;
  if (el1 != null && el2 != null) {
    if (el1.value.length > 0 && el2.value.length > 0) {
      resultButton.disabled = false;
    }
  }
}


function haveElementWithValueOrUndefinedArg(elName) {
  if (elName == undefined) {
    return true;
  }
  el = document.getElementById(elName);
  if (el != null && el.value.length > 0) {
    return true;
  }
  return false;
}

function enableIfElementsHaveSomeValue(resultName, el1name, el2name, el3name) {
  resultButton = document.getElementById(resultName);
  // by default it's disabled. It's enabled if certain special condition is met
  resultButton.disabled = true;
  if (haveElementWithValueOrUndefinedArg(el1name) && haveElementWithValueOrUndefinedArg(el2name)
      && haveElementWithValueOrUndefinedArg(el3name)) {
    resultButton.disabled = false;
  }
}

function enableIfCountry1And2Selected() {
  enableIfElementsHaveSomeValue("compare", "country1", "country2");
}

function enableIfCity1And2Selected() {
  enableIfElementsHaveSomeValue("compare", "city1", "city2");
}

function startsWith(name, prefix) {
  if (name == undefined) {
    return false;
  }
  if (name.length < prefix.length) {
    return false;
  }
  return name.substr(0, prefix.length) == prefix;
}

function checkByName(nameprefix, visible)  {
  var allTags = document.getElementsByTagName('*');
  for (var i = 0; i < allTags.length; i++) {
    var tag =  allTags[i];
    var name = tag.name;
    if (name != undefined && startsWith(tag.name, nameprefix)) {
      allTags[i].checked = visible;
    }
  }
  return true;
}
