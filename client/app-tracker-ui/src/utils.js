export function getOption(selectEle) {
    const options = selectEle.options;
    const idx = selectEle.selectedIndex;
    
    return options[idx].value;
}

export function getTodayString() {
    return new Date().toISOString().substring(0, 10);
}

export function getNicerString(val) {
    const words = val.split('_');

    for (let i = 0; i < words.length; i++) {
        words[i] = titleCase(words[i])
    }

    return words.join(' ');
}

function titleCase(val) {
    return val.charAt(0).toUpperCase() + val.substring(1).toLowerCase();
}